# Firebase Setup Guide for FlatPoolManager

This guide will walk you through setting up Firebase for the FlatPoolManager app.

## Prerequisites
- Google account
- Android Studio installed
- FlatPoolManager project open

## Step 1: Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click **"Add project"** or **"Create a project"**
3. Enter project name: `FlatPoolManager` (or any name you prefer)
4. Click **Continue**
5. Disable Google Analytics (optional for this project) or keep it enabled
6. Click **Create project**
7. Wait for the project to be created, then click **Continue**

## Step 2: Add Android App to Firebase

1. In your Firebase project dashboard, click the **Android icon** to add an Android app
2. Register your app with these details:
   - **Android package name**: `com.divyansh.flatpoolmanager`
   - **App nickname** (optional): `FlatPoolManager`
   - **Debug signing certificate SHA-1** (optional for now): Leave empty
3. Click **Register app**

## Step 3: Download google-services.json

1. Click **Download google-services.json**
2. Save the file to your computer
3. **IMPORTANT**: Move this file to your project's `app/` directory
   - Path should be: `e:/FlatPoolManager/app/google-services.json`
   - You can drag and drop it in Android Studio's Project view under the `app` folder

## Step 4: Enable Firebase Authentication

1. In Firebase Console, click **Build** → **Authentication** in the left sidebar
2. Click **Get started**
3. Click on **Email/Password** in the Sign-in providers list
4. Toggle **Enable** to ON
5. Click **Save**

## Step 5: Enable Firebase Realtime Database

1. In Firebase Console, click **Build** → **Realtime Database** in the left sidebar
2. Click **Create Database**
3. Select a location (choose closest to your region):
   - For India: `asia-southeast1` (Singapore)
   - Or choose your preferred location
4. Click **Next**
5. Start in **Test mode** (we'll update security rules later)
6. Click **Enable**

## Step 6: Set Up Database Security Rules

1. In the Realtime Database page, click the **Rules** tab
2. Replace the default rules with:

```json
{
  "rules": {
    "pool": {
      ".read": "auth != null",
      ".write": "auth != null"
    },
    "payments": {
      ".read": "auth != null",
      ".write": "auth != null",
      "$paymentId": {
        ".validate": "newData.hasChildren(['flatmateName', 'amount', 'itemName', 'date', 'isContribution', 'userId'])"
      }
    },
    "messages": {
      ".read": "auth != null",
      ".write": "auth != null",
      "$messageId": {
        ".validate": "newData.hasChildren(['senderId', 'senderName', 'message', 'timestamp'])"
      }
    }
  }
}
```

3. Click **Publish**

## Step 7: Create Test Users in Firebase Authentication

You need to manually create the 6 users in Firebase Authentication:

1. Go to **Authentication** → **Users** tab
2. Click **Add user** for each of these users:

| Email | Password | Name |
|-------|----------|------|
| divyansh@flatpool.com | divyansh123 | Divyansh |
| tanwar@flatpool.com | tanwar123 | Tanwar |
| jangir@flatpool.com | jangir123 | Jangir |
| sahil@flatpool.com | sahil123 | Sahil |
| jashan@flatpool.com | jashan123 | Jashan |
| ritu@flatpool.com | ritu123 | Ritu |

For each user:
1. Click **Add user**
2. Enter the email
3. Enter the password
4. Click **Add user**

## Step 8: Verify Setup

### Check google-services.json location:
```
FlatPoolManager/
├── app/
│   ├── google-services.json  ← Should be here
│   ├── build.gradle.kts
│   └── src/
```

### Build the project:
```bash
./gradlew build
```

If you see errors about `google-services.json` not found, make sure the file is in the correct location.

## Step 9: Optional - Get Your Database URL

1. In Firebase Console, go to **Realtime Database**
2. Copy your database URL (looks like: `https://flatpoolmanager-xxxxx.firebaseio.com/`)
3. You may need this URL in the app configuration (we'll handle this in code)

## Troubleshooting

### Error: "google-services.json not found"
- Make sure the file is in `app/` directory, not in the root project directory
- Sync Gradle files: File → Sync Project with Gradle Files

### Error: "Default FirebaseApp is not initialized"
- Make sure `google-services.json` is in the correct location
- Check that the package name in `google-services.json` matches `com.divyansh.flatpoolmanager`
- Clean and rebuild: Build → Clean Project, then Build → Rebuild Project

### Authentication not working
- Verify Email/Password authentication is enabled in Firebase Console
- Check that test users are created in Authentication → Users

### Database permission denied
- Make sure you're in Test mode or have proper security rules
- Verify the user is authenticated before accessing database

## Next Steps

Once Firebase is set up:
1. ✅ Build the project to verify configuration
2. ✅ Run the app
3. ✅ Test login with one of the predefined users
4. ✅ Test adding money and expenses
5. ✅ Test chat feature

## Important Notes

⚠️ **Test Mode Security**: The database is currently in test mode, which allows read/write access to authenticated users. This is fine for development but should be updated with proper security rules before production use.

⚠️ **Credentials**: The user credentials listed above are for testing only. In a production app, users should be able to create their own accounts and set their own passwords.

🔒 **Security**: Never commit `google-services.json` to a public repository. Add it to `.gitignore` if you plan to share your code publicly.
