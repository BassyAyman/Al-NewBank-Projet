import {createClient, RedisClientType} from 'redis';

const redisUrl = `redis://${process.env.REDIS_HOST}:6379`;

function resetAllRedisDebits() {
    const client: RedisClientType = createClient({
        url: redisUrl,
    });

    client.on('error', (err: Error) => {
        console.error('Redis error', err);
    });

    client.connect().then(() => {
        console.log('Connected to Redis');
    });

    setAmount(client, 0);
}

/**
 * Set amount for all accounts
 * @param amount
 */
const setAmount = async (client: RedisClientType,amount: number) => {
    try {
        // get all account ids
        const keys: string[] = await client.keys('*');

        for (const key of keys) {
            await client.set(key, amount.toString());
        }
        console.log('All debit values reset to 0 in redis');
    } catch (err) {
        console.error('Error while reseting debit values in redit', err);
    } finally {
        await client.quit().then(() => {
            console.log('Closed redis connection');
        });
    }
};

export default resetAllRedisDebits;
