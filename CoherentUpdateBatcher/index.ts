import express from "express";
import {connectToPostgres, getAllCustomers, updateAccountBalance} from "./resetPostgresMaster";
import {connectToRedis, resetDebitForCustomer} from "./resetRedis";

const app = express();
const port = 8083;
app.use(express.json());

app.get('/', (req, res) => {
    res.send("send a PUT request on / to reset all debits");
});

app.put('/', async (req, res) => {
    const pgClient = await connectToPostgres();
    const redisClient = await connectToRedis();

    const customerIds: number[] = await getAllCustomers(pgClient);
    console.log(`Found ${customerIds.length} customers`);
    for (const customerId of customerIds) {
        console.log(`Updateing balance of customer ${customerId}`);
        await updateAccountBalance(pgClient, customerId);
        await resetDebitForCustomer(redisClient, customerId);
    }

    res.send("Request received");
});

app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
});