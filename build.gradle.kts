buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
}