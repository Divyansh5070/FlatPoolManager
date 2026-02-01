# FlatPoolManager - User Credentials

## Predefined Users

For testing and demo purposes, the app has 6 predefined users:

### 1. Divyansh (Admin)
- **Email**: `divyansh@flatpool.com`
- **Password**: `divyansh123`
- **Role**: Admin (Can add money to pool + add expenses)
- **Avatar Color**: Blue

### 2. Tanwar
- **Email**: `tanwar@flatpool.com`
- **Password**: `tanwar123`
- **Role**: Member (Can only add expenses)
- **Avatar Color**: Green

### 3. Jangir
- **Email**: `jangir@flatpool.com`
- **Password**: `jangir123`
- **Role**: Member (Can only add expenses)
- **Avatar Color**: Orange

### 4. Sahil
- **Email**: `sahil@flatpool.com`
- **Password**: `sahil123`
- **Role**: Member (Can only add expenses)
- **Avatar Color**: Purple

### 5. Jashan
- **Email**: `jashan@flatpool.com`
- **Password**: `jashan123`
- **Role**: Member (Can only add expenses)
- **Avatar Color**: Teal

### 6. Ritu
- **Email**: `ritu@flatpool.com`
- **Password**: `ritu123`
- **Role**: Member (Can only add expenses)
- **Avatar Color**: Pink

## Quick Login

The login screen includes quick login buttons for each user for easy testing. Simply tap on a user's button to automatically fill in their credentials.

## Role Permissions

### Admin (Divyansh only)
- ✅ Add money to pool
- ✅ Add expenses
- ✅ View transaction history
- ✅ Use chat
- ✅ View profile

### Members (All others)
- ❌ Cannot add money to pool
- ✅ Add expenses
- ✅ View transaction history
- ✅ Use chat
- ✅ View profile

## Security Note

⚠️ **These credentials are for testing/demo purposes only.** In a production environment:
- Users should create their own accounts
- Passwords should be strong and unique
- Consider implementing password reset functionality
- Add email verification
- Implement proper role management system
