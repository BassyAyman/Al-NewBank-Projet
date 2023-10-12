const express = require('express');
const app = express();
const config = require('./config');
const port = config.port;

app.use(express.json());

app.get('/', (req, res) => {
    res.send('Terminal Payment Gateway is running');
});

app.post('/newTransaction', (req, res) => {
    res.status(201).json("Transaction created");
});

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
