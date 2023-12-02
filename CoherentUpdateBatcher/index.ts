import express from "express";
import resetAllRedisDebits from "./resetRedis";
import resetAllPostgresDebits from "./resetPostgresMaster";

const app = express();
const port = 8083;
app.use(express.json());

app.get('/', (req, res) => {
    res.send("send a PUT request on / to reset all debits");
});

app.put('/', (req, res) => {
    console.log("Resetting all debits in Redis...");
    resetAllRedisDebits();
    console.log("Resetting all debits in Postgres master...");
    resetAllPostgresDebits();
    res.send("Request received");
});

app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
});