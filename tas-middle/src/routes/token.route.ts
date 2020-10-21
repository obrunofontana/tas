import { Request, Response, Router } from 'express';
import jwt from 'jsonwebtoken';

class TokenRoute {
    
    public router: Router;

    constructor() {
        this.router = Router();
        this.init();
    }

    private init(): void {
        this.router.post('/', (req: Request, res: Response) => {
            
            if (req.body.appKey === process.env.APP_KEY) {
                const payload = {appkey: process.env.APP_KEY};

                res.json({
                    token: jwt.sign(payload, process.env.SECRET, {
                        expiresIn: '60 seconds'
                    })
                });
            } else {
                res.status(401).json({message: 'Invalid Credential'});
            }
                        
        });
    }
}

export default new TokenRoute().router;
