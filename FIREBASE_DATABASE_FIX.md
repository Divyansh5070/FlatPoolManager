# Firebase Realtime Database Not Working - Fix Guide

## 🔴 Problem

Your Firebase Realtime Database isn't working because:

1. **Database might not be created** in Firebase Console
2. **Security rules are too restrictive** with validation
3. **Database URL might be missing** from configuration

## ✅ Solution

### Step 1: Create Realtime Database

1. Go to https://console.firebase.google.com/
2. Select project: **flatpoolmanager**
3. Click **"Realtime Database"** in left sidebar
4. Click **"Create Database"** button
5. Choose location:
   - For India: **asia-southeast1 (Singapore)**
   - For US: **us-central1**
6. Select **"Start in test mode"**
7. Click **"Enable"**

### Step 2: Fix Security Rules

Your current rules have validation that's blocking writes. Replace with:

```json
{
  "rules": {
    "pool": {
      ".read": "auth != null",
      ".write": "auth != null"
    },
    "payments": {
      ".read": "auth != null",
      ".write": "auth != null"
    }
  }
}
```

**How to update:**
1. In Firebase Console → Realtime Database
2. Click **"Rules"** tab
3. Replace all content with above
4. Click **"Publish"**

### Step 3: Get Database URL

After creating the database, you'll see a URL like:
```
https://flatpoolmanager-default-rtdb.firebaseio.com/
```

**Copy this URL** - you might need it.

### Step 4: Re-download google-services.json

1. Firebase Console → Project Settings (gear icon)
2. Scroll to "Your apps" section
3. Find your Android app
4. Click **"google-services.json"** download button
5. Replace the file in `app/` folder
6. Rebuild app: `./gradlew clean installDebug`

## 🧪 Test After Setup

1. **Login** to the app
2. **Try adding money** (as Divyansh)
3. **Check Firebase Console**:
   - Go to Realtime Database → Data tab
   - You should see:
     ```
     flatpoolmanager-default-rtdb
     ├─ pool
     │  └─ balance: 1000
     └─ payments
        └─ [auto-generated-id]
           ├─ amount: 1000
           ├─ flatmateName: "Divyansh"
           └─ ...
     ```

## 🔍 Debugging

If still not working, check logcat:

```bash
adb logcat | findstr "Firebase"
```

**Look for errors like:**
- "DatabaseException: Permission denied"
- "FirebaseDatabase: Can't determine Firebase Database URL"
- "Failed to get FirebaseDatabase instance"

## 📊 Common Errors

### Error: "Permission denied"
**Cause**: Security rules blocking access
**Fix**: Use test mode rules (see Step 2)

### Error: "Database URL not found"
**Cause**: Realtime Database not created
**Fix**: Create database (see Step 1)

### Error: "Auth is null"
**Cause**: Not logged in
**Fix**: Make sure you're logged in before adding data

## ✅ Checklist

- [ ] Realtime Database created in Firebase Console
- [ ] Database URL visible in Firebase Console
- [ ] Security rules set to test mode
- [ ] google-services.json re-downloaded
- [ ] App rebuilt and installed
- [ ] Logged in as a user
- [ ] Tried adding money/expense

---

**After completing these steps, adding money and expenses should work!**
