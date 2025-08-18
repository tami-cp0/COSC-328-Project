#!/bin/bash

# Simple Android Notes App - Command Line Build Setup
# This script helps you build the Android app without Android Studio

echo "🚀 Android Notes App - Command Line Setup"
echo "=========================================="

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 8 or higher."
    echo "   On Ubuntu/Debian: sudo apt install openjdk-11-jdk"
    echo "   On CentOS/RHEL: sudo yum install java-11-openjdk-devel"
    exit 1
fi

echo "✅ Java found: $(java -version 2>&1 | head -n 1)"

# Check if Android SDK is set up
if [ -z "$ANDROID_HOME" ]; then
    echo ""
    echo "⚠️  ANDROID_HOME environment variable is not set."
    echo ""
    echo "To build Android apps, you need to:"
    echo "1. Download Android Command Line Tools from:"
    echo "   https://developer.android.com/studio#command-tools"
    echo ""
    echo "2. Extract and set up the SDK:"
    echo "   mkdir -p ~/android-sdk/cmdline-tools"
    echo "   unzip commandlinetools-linux-*.zip -d ~/android-sdk/cmdline-tools/"
    echo "   mv ~/android-sdk/cmdline-tools/cmdline-tools ~/android-sdk/cmdline-tools/latest"
    echo ""
    echo "3. Set environment variables in ~/.bashrc:"
    echo "   export ANDROID_HOME=~/android-sdk"
    echo "   export PATH=\$PATH:\$ANDROID_HOME/cmdline-tools/latest/bin"
    echo "   export PATH=\$PATH:\$ANDROID_HOME/platform-tools"
    echo ""
    echo "4. Install required SDK components:"
    echo "   sdkmanager 'platform-tools' 'platforms;android-34' 'build-tools;34.0.0'"
    echo ""
    echo "5. Accept licenses:"
    echo "   sdkmanager --licenses"
    echo ""
    echo "After setting up Android SDK, you can build with:"
    echo "   ./gradlew assembleDebug"
    echo ""
    exit 1
else
    echo "✅ ANDROID_HOME found: $ANDROID_HOME"
fi

# Check if local.properties exists
if [ ! -f "local.properties" ]; then
    echo "📝 Creating local.properties file..."
    echo "sdk.dir=$ANDROID_HOME" > local.properties
    echo "✅ local.properties created"
else
    echo "✅ local.properties exists"
fi

echo ""
echo "🔨 Available build commands:"
echo "   ./gradlew assembleDebug     - Build debug APK"
echo "   ./gradlew assembleRelease   - Build release APK"
echo "   ./gradlew installDebug      - Install debug APK to connected device"
echo "   ./gradlew clean             - Clean build files"
echo ""
echo "📱 The built APK will be in: app/build/outputs/apk/"
echo ""

# Try to build
echo "🔨 Attempting to build debug APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "🎉 SUCCESS! Your Notes app has been built!"
    echo "📱 APK location: app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "To install on your Android device:"
    echo "1. Enable Developer Options and USB Debugging on your device"
    echo "2. Connect your device via USB"
    echo "3. Run: ./gradlew installDebug"
    echo ""
    echo "Or manually install the APK:"
    echo "1. Transfer app-debug.apk to your device"
    echo "2. Enable 'Install from Unknown Sources' in device settings"
    echo "3. Open the APK file on your device to install"
else
    echo ""
    echo "❌ Build failed. Check the error messages above."
    echo "Common issues:"
    echo "- Android SDK not properly installed"
    echo "- Missing SDK components"
    echo "- Java version compatibility"
fi
