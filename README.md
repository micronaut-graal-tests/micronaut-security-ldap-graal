# Micronaut Security LDAP Graal

Test application for Micronaut Security LDAP and GraalVM

Get the Bearer token with:
```bash
ACCESS_TOKEN=$(curl -s -X POST -H 'Content-Type:application/json' -d '{"username":"euler","password":"password"}' localhost:8080/login | jq -r .access_token)
```

Use it:

```bash
echo $ACCESS_TOKEN
curl -H "Authorization:Bearer ${ACCESS_TOKEN}" localhost:8080/
```
