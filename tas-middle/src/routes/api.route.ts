import { NextFunction, Request, Response, Router } from 'express';
import jwt from 'jsonwebtoken';
import axios from 'axios';
import dotenv from 'dotenv';

class APIRoute {
  public router: Router;
  public url: string;

  constructor() {
    dotenv.config();
    this.url = process.env.SERVER;
    this.router = Router();
    this.init();
  }

  private validateToken(req: Request): void {
    if (
      req.headers.authorization &&
      req.headers.authorization.split(' ')[0] == 'Bearer'
    ) {
      let token = req.headers.authorization.split(' ')[1];

      try {
        jwt.verify(token, process.env.SECRET);
      } catch (error) {
        throw { message: error.name == 'TokenExpiredError' ? 'Token expired' : 'Token invalid' };
      }
    } else {
      throw { message: 'Request unauthorized' };
    }
  }

  private init(): void {
    this.router.route('/:resource')
      .all((req: Request, res: Response, next: NextFunction) => {
        try {
          this.validateToken(req);
          next();
        } catch (error) {
          res.status(401).json(error);
        }
      })
      .get(async (req: Request, res: Response) => {     
        try {
          const response = await axios.get(`${this.url}/${req.params.resource}`);
          const { data, status } = response;

          return res.status(status).json(data);
        } catch (e) {
          return res.status(500).json({ error: e });
        }       
      })
      .post( async (req: Request, res: Response) => {
        try {
          const response = await axios.post(`${this.url}/${req.params.resource}`, req.body);
          const { data, status } = response;
          
          return res.status(status).json(data)
        } catch (e) {
          console.log('ehhh karalho ', e);
          return res.status(500).json({ error: e });
        }
      });

    // Resource with ID
    this.router.route('/:resource/:id')
      .all((req: Request, res: Response, next: NextFunction) => {
        try {
          this.validateToken(req);
          next();
        } catch (e) {
          return res.status(401).json(e);
        }
      })
      .get( async (req: Request, res: Response) => {
        try {
          const response = await axios.get(`${this.url}/${req.params.resource}/${req.params.id}`);
          const { data, status } = response;

          return res.status(status).json(data);
        } catch (e) {
          const { status, data } = e.response;
          return res.status(status).json({ error: data });
        }     
      })
      .put( async (req: Request, res: Response) => {
        try {
          const response = await axios.put(`${this.url}/${req.params.resource}/${req.params.id}`, req.body);
          const { data, status } = response;

          return res.status(status).json(data);
        } catch (e) {
          const { status, data } = e.response;
          return res.status(status).json({ error: data });
        }  
      })
      .delete( async (req: Request, res: Response) => {
        try {
          const response = await axios.delete(`${this.url}/${req.params.resource}/${req.params.id}`);
          const { data, status } = response;

          return res.status(status).json(data);
        } catch (e) {
          const { status, data } = e.response;
          return res.status(status).json({ error: data });
        }  
      });
  }
}

export default new APIRoute().router;
