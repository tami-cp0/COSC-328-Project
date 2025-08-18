# COSC-328 Notes App

An Android notes application for managing personal notes.

## How to Build and Run

### Prerequisites
- Android Studio
- Java 8+

### Build Steps

1. **Open in Android Studio:**
   - Open the project folder in Android Studio
   - Let Gradle sync automatically

2. **Build the APK:**
   ```bash
   ./gradlew assembleDebug
   ```
   The APK will be generated in `app/build/outputs/apk/debug/`

3. **Run on device/emulator:**
   - Connect an Android device or start an emulator
   - Click "Run" in Android Studio, or install the APK manually

### Features
- Add, edit, and delete notes
- Simple and intuitive interface
- Local storage

For questions, contact the repository owner.
│   ├── NotesAdapter.kt          # RecyclerView adapter for notes list
│   └── NotesRepository.kt       # Data repository for note operations
├── res/
│   ├── layout/
│   │   ├── activity_main.xml           # Main activity layout
│   │   ├── activity_add_edit_note.xml  # Add/edit note layout
│   │   └── item_note.xml              # Individual note item layout
│   ├── values/
│   │   ├── colors.xml          # App color scheme
│   │   ├── strings.xml         # String resources
│   │   └── themes.xml          # App themes
│   └── xml/
│       ├── backup_rules.xml
│       └── data_extraction_rules.xml
└── AndroidManifest.xml
```

## How to Build and Run

 
### Prerequisites
-
- Android Studio (latest version recommended)
- Android SDK with API level 34
- Kotlin plugin

### Build Steps

1. **Open the project in Android Studio**:
   
   ```bash
   # Navigate to the project directory
   cd /home/ayo/notes_app
   
   # Open with Android Studio
   studio .
   ```

2. **Sync Gradle files**: Android Studio will automatically sync the Gradle files when you open the project.

3. **Build the project**:
   - In Android Studio: Build → Make Project
   - Or use the command line:
   
   ```bash
   ./gradlew build
   ```

4. **Run on device/emulator**:
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - Or use the command line:
   ```bash
   ./gradlew installDebug
   ```

### Manual Build (Command Line)

If you prefer to build without Android Studio:

```bash
# Make sure you have Android SDK and build-tools installed
# Set ANDROID_HOME environment variable

# Build debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Or build and install in one step
./gradlew build installDebug
```

## Usage

1. **Adding a Note**: Tap the floating action button (+) to create a new note
2. **Viewing Notes**: All notes are displayed in the main screen with title, content preview, and date
3. **Editing a Note**: Tap on any note to open it for editing
4. **Deleting a Note**: Tap the delete button (trash icon) on any note and confirm deletion

## Dependencies

- AndroidX Core KTX
- AppCompat
- Material Design Components
- ConstraintLayout
- RecyclerView
- CardView
- Gson (for JSON serialization)
- Lifecycle components

## License

This project is created for educational purposes.
