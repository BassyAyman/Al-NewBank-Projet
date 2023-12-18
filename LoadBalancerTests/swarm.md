# How to setup docker swarm
```bash
docker swarm init
```
# How to launch
```bash
docker stack deploy -c docker-compose.yml mystack
```
# How to scale up
```bash
docker service scale mystack_server=2
```
# How to scale down
```bash
docker service scale mystack_server=1
```
# How to remove
```bash
docker stack rm mystack
```