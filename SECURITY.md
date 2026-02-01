# Security Policy

## Reporting a Vulnerability

If you discover a security vulnerability in FlatPoolManager, please report it by emailing [your-email@example.com].

## Demo Credentials

This repository contains demo credentials for testing purposes:
- These credentials are **publicly visible** and for **demonstration only**
- They do NOT provide access to any real user data or production systems
- Users must create these test accounts in their own Firebase project
- The credentials only work with Firebase projects you control

## Best Practices for Your Deployment

When deploying your own version of FlatPoolManager:

1. **Never use the demo passwords** for real accounts
2. **Create unique users** in your Firebase Authentication console
3. **Use strong passwords** for any real deployment
4. **Enable Firebase security rules** to protect your database
5. **Keep `google-services.json` private** (already in .gitignore)
6. **Use environment variables** for sensitive configuration (see `.env.example`)

## Production Recommendations

For a production deployment, implement:
- User registration and account creation
- Password reset functionality
- Email verification
- Two-factor authentication (2FA)
- Proper role management system
- Regular security audits

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |

## Security Features

- Firebase Authentication for secure login
- Role-based access control (RBAC)
- Firebase Realtime Database security rules
- Client-side input validation

---

**Note**: This is a demo/educational project. For production use, additional security measures should be implemented.
