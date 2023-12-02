import {Client} from 'pg';

function resetAllPostgresDebits() {
    const client = new Client({
        user: 'postgres',
        host: process.env.POSTGRES_HOST,
        database: 'clientinfo_db',
        port: 5432,
    });

    client.connect().then(() => {
        console.log('Connected to Postgres');
    });

    updateAccountBalances(client);
}

const updateAccountBalances = async (client: Client) => {
    try {
        // update account balance and reset in_debit_amount
        const query = 'UPDATE account SET amount_money = amount_money - in_debit_amount, in_debit_amount = 0;';
        const res = await client.query(query);

        console.log('Number of customer accounts updated:', res.rowCount);
    } catch (err) {
        console.error('Error while updating accounts', err);
    } finally {
        await client.end().then(() => {
            console.log('Disconnected from Postgres');
        });
    }
};

export default resetAllPostgresDebits;
