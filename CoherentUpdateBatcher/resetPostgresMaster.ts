import {Client} from 'pg';

export async function connectToPostgres() {
    const client = new Client({
        user: 'postgres',
        host: process.env.POSTGRES_HOST,
        database: 'clientinfo_db',
        port: 5432,
    });

    try {
        await client.connect();
        console.log('Connected to Postgres');
        return client;
    } catch (error) {
        console.error('Failed to connect to Postgres', error);
        throw error;
    }
}

export async function getAllCustomers(client: Client): Promise<number[]> {
    try {
        const query = 'SELECT id FROM account;';
        return (await client.query(query)).rows.map((row) => row.id);
    } catch (err) {
        console.error('Error while getting all customers', err);
        throw err;
    }
}

export async function updateAccountBalance(client: Client, customerId: number) {
    try {
        // update account balance and reset in_debit_amount
        const query = 'UPDATE account SET amount_money = amount_money - in_debit_amount, in_debit_amount = 0 WHERE id = $1;';
        const res = await client.query(query, [customerId]);
    } catch (err) {
        console.error('Error while updating accounts', err);
        throw err;
    }
}
