# Spring-Security-Asymmetric-Encryption

# Encryption and OpenSSL Key Generation Guide

## Understanding Encryption Types

### Symmetric Encryption

Symmetric encryption uses the **same key** for both encryption and decryption. Both the sender and receiver must possess the identical secret key to communicate securely.

**How it works:**
- Alice and Bob share the same secret key (e.g., "MySecretKey123")
- Alice encrypts her message using this key
- Bob decrypts the message using the same key

**Example:**
```
Original Message: "Hello World"
Shared Key: "MySecretKey123"
Encrypted Message: "X7k9mP2qL8nR4vT6"
Decrypted Message: "Hello World" (using the same key)
```

**Advantages:**
- Fast and efficient for large amounts of data
- Lower computational overhead

**Disadvantages:**
- Key distribution problem: How do you securely share the key?
- Key management becomes complex with multiple parties

### Asymmetric Encryption (Public-Key Cryptography)

Asymmetric encryption uses a **pair of mathematically related keys**: a public key and a private key. What one key encrypts, only the other key can decrypt.

**How it works:**
- Each person has a key pair: one public (shareable) and one private (secret)
- Alice encrypts messages using Bob's public key
- Only Bob can decrypt these messages using his private key
- Bob can encrypt responses using Alice's public key

**Example:**
```
Bob's Key Pair:
- Public Key: "Bob_Public_2048bit..." (shared openly)
- Private Key: "Bob_Private_2048bit..." (kept secret)

Alice wants to send Bob a message:
1. Alice encrypts "Hello Bob" using Bob's public key
2. Encrypted message: "M8x7K9pL3qN6vR2T..."
3. Only Bob can decrypt this using his private key
4. Bob decrypts to read: "Hello Bob"
```

**Advantages:**
- Solves the key distribution problem
- Enables secure communication without prior key sharing
- Supports digital signatures for authentication

**Disadvantages:**
- Slower than symmetric encryption
- Higher computational requirements

## OpenSSL Key Generation Commands

The following commands generate an RSA key pair for asymmetric encryption:

### Command 1: Generate Private Key
```bash
openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048
```

**Breakdown:**
- `openssl genpkey`: OpenSSL command to generate a private key
- `-algorithm RSA`: Specifies the RSA algorithm for key generation
- `-out private_key.pem`: Output filename for the private key
- `-pkeyopt rsa_keygen_bits:2048`: Sets the key size to 2048 bits (industry standard for security)

**What this creates:**
- A private key file named `private_key.pem`
- Contains both private and public key information
- 2048-bit key length provides strong security
- PEM format (Privacy-Enhanced Mail) - a base64 encoded format

### Command 2: Extract Public Key
```bash
openssl rsa -pubout -in private_key.pem -out public_key.pem
```

**Breakdown:**
- `openssl rsa`: OpenSSL RSA key processing command
- `-pubout`: Extract and output the public key component
- `-in private_key.pem`: Input file containing the private key
- `-out public_key.pem`: Output filename for the extracted public key

**What this creates:**
- A separate public key file named `public_key.pem`
- Contains only the public key portion
- Can be safely shared with others
- Also in PEM format

## File Contents Example

After running these commands, you'll have two files:

**private_key.pem** (Keep this secret!)
```
-----BEGIN PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7...
[Long base64 encoded string]
-----END PRIVATE KEY-----
```

**public_key.pem** (Safe to share)
```
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu8F7L9k...
[Shorter base64 encoded string]
-----END PUBLIC KEY-----
```

## Security Best Practices

- **Never share your private key** - treat it like a password
- Store private keys in secure locations with proper file permissions
- Use at least 2048-bit keys (4096-bit for higher security requirements)
- Regularly rotate keys in production environments
- Consider using hardware security modules (HSMs) for critical applications

## Common Use Cases

- **SSL/TLS certificates** for websites
- **SSH authentication** for secure server access
- **Code signing** to verify software integrity
- **Email encryption** (PGP/GPG)
- **Digital signatures** for document authentication