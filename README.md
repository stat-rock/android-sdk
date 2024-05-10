# StatRock Android SDK

## Download

Add StatRock Maven repository in project `build.gradle` file:

```gradle
allprojects {
    repositories {
        maven {
            url 'https://raw.githubusercontent.com/stat-rock/android-sdk/master/releases'
        }
        ...
}
```

or in settings project `settings.gradle` file:

```gradle
dependencyResolutionManagement {
    ...
    repositories {
        ....
        maven {
            url 'https://raw.githubusercontent.com/stat-rock/android-sdk/master/releases'
        }
    }
}
```

Then, add the library dependency to app module `build.gradle`:
```gradle
dependencies {
    ...
    implementation 'com.statrock.sdk:android.release:1.4.0''
}
```

## Usage

Initialize SDK using your unique CLIENT_KEY string on application create:

```xml
    <com.statrock.sdk.StatRockView
        android:id="@+id/statrock"
        android:layout_width="match_parent"
        android:layout_height="200dp" />
```

```java

class SimpleFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //... init view
        StatRockView statRock = view.findViewById(R.id.statrock);
        statRock.load("Hr5pC_SLH6PV");
        
        return view;
    }
}
```

## Demo project
https://github.com/stat-rock/android-sdk
