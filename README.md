# Kanflow API

# how to run


- Create RSA keys for JWT:

```
# create keys location and cd to it
mkdir -p src/main/resources/certs/ && cd src/main/resources/certs/

# create key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# extract private key
openssl pkcs8 -in keypair.pem -topk8 -nocrypt -inform PEM -outform PEM -out private.pem
```

- Setup env:
```
# copy .env.example to .env
cp .env.example .env
# add the appropriate env value in the .env file
```

- Run the server
```
# using the Makefile
make local
```
