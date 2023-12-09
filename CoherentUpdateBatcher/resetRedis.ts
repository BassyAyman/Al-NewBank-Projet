import {createClient, RedisClientType} from 'redis';

export async function connectToRedis() {
    const client: RedisClientType = createClient({
        url: `redis://${process.env.REDIS_HOST}:6379`,
    });

    client.on('error', (err: Error) => {
        console.error('Redis error', err);
    });

    await client.connect().then(() => {
        console.log('Connected to Redis');
    });

    return client;
}

export async function resetDebitForCustomer(client: RedisClientType, customerId: number) {
    await client.set(customerId.toString(), '0');
}
