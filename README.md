# FlatPoolManager - Enhanced Version

A modern Android app for managing shared expenses among flatmates with real-time synchronization, role-based permissions, and integrated chat.

## ✨ Features

- 🔐 **Secure Login System** - 6 predefined users with Firebase Authentication
- 👑 **Role-Based Access Control** - Admin can add money, members can only add expenses
- 💰 **Real-Time Pool Management** - Track shared expenses with live updates
- 💬 **Integrated Chat** - Communicate with flatmates in real-time
- 📱 **Modern UI** - Beautiful gradients, smooth animations, and intuitive navigation
- ☁️ **Cloud Sync** - Firebase Realtime Database for multi-device synchronization

## 🚀 Quick Start

### Prerequisites

- Android Studio (latest version)
- Android device or emulator (API 24+)
- Firebase account

### Setup

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd FlatPoolManager
   ```

2. **Set up Firebase** (REQUIRED)
   - Follow the detailed guide in [FIREBASE_SETUP.md](FIREBASE_SETUP.md)
   - Download `google-services.json` and place in `app/` directory
   - Create 6 test users in Firebase Authentication

3. **Build and run**
   ```bash
   ./gradlew build
   ./gradlew installDebug
   ```

## 👥 Predefined Users

> [!WARNING]
> **These are DEMO credentials for testing purposes only!**
> You must create these users in YOUR Firebase Authentication console for the app to work.
> See [FIREBASE_SETUP.md](FIREBASE_SETUP.md) for instructions.

| Name | Email | Password | Role |
|------|-------|----------|------|
| Divyansh | divyansh@flatpool.com | divyansh123 | Admin |
| Tanwar | tanwar@flatpool.com | tanwar123 | Member |
| Jangir | jangir@flatpool.com | jangir123 | Member |
| Sahil | sahil@flatpool.com | sahil123 | Member |
| Jashan | jashan@flatpool.com | jashan123 | Member |
| Ritu | ritu@flatpool.com | ritu123 | Member |

See [USER_CREDENTIALS.md](USER_CREDENTIALS.md) for more details.

## 📱 Screens

### Login Screen
- Beautiful gradient UI
- Quick login buttons for easy testing
- Password visibility toggle

### Home Screen
- Animated balance card with color coding
- Transaction history
- Role-based action buttons
- User profile in top bar

### Chat Screen
- Real-time messaging
- Message bubbles with user avatars
- Auto-scroll to latest messages

### Profile Screen
- User information display
- Permissions overview
- Logout functionality

## 🏗️ Architecture

- **MVVM Pattern** - Clean separation of concerns
- **Jetpack Compose** - Modern declarative UI
- **Firebase** - Backend as a Service
  - Authentication
  - Realtime Database
- **Navigation Component** - Type-safe navigation
- **Kotlin Coroutines** - Asynchronous programming
- **StateFlow** - Reactive state management

## 📁 Project Structure

```
app/src/main/java/com/divyansh/flatpoolmanager/
├── data/              # Data models
├── repository/        # Firebase operations
├── viewmodel/         # Business logic
├── screens/           # UI screens
├── navigation/        # Navigation setup
└── ui/theme/          # App theming
```

## 🔧 Technologies Used

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Backend**: Firebase (Auth + Realtime Database)
- **Architecture**: MVVM
- **Navigation**: Navigation Compose
- **Async**: Kotlin Coroutines & Flow
- **Dependency Injection**: Manual (ViewModelFactory)

## 📖 Documentation

- [Firebase Setup Guide](FIREBASE_SETUP.md) - Step-by-step Firebase configuration
- [User Credentials](USER_CREDENTIALS.md) - List of test users
- [Walkthrough](walkthrough.md) - Detailed feature walkthrough

## 🎯 Key Permissions

### Admin (Divyansh)
- ✅ Add money to pool
- ✅ Add expenses
- ✅ View transactions
- ✅ Use chat
- ✅ Delete transactions

### Members (Others)
- ❌ Cannot add money
- ✅ Add expenses
- ✅ View transactions
- ✅ Use chat
- ✅ Delete transactions

## 🐛 Troubleshooting

### Build Errors
- Ensure `google-services.json` is in `app/` directory
- Sync Gradle files after adding Firebase config

### Login Issues
- Verify users are created in Firebase Authentication
- Check email/password match exactly

### Real-time Sync Issues
- Confirm Firebase Realtime Database is enabled
- Check internet connection
- Verify database security rules

## 📝 License

This project is for educational purposes.

## 🤝 Contributing

This is a personal project for managing flat expenses. Feel free to fork and customize for your needs!

## 📞 Support

For setup help, see:
- [FIREBASE_SETUP.md](FIREBASE_SETUP.md)
- [Walkthrough Guide](walkthrough.md)

---

**Built with ❤️ for flatmates**
