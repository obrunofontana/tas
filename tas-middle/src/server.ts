import dotenv from 'dotenv';
import moment from 'moment';

import app from './app';

dotenv.config();

app.listen(process.env.PORT, () => {
  console.log(
    `[INFO] ${moment().format('LLL')}: Server started and running on port ${
      process.env.PORT
    }...`
  );
});
