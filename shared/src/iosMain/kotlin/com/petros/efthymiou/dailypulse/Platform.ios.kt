package com.petros.efthymiou.dailypulse

import platform.UIKit.UIDevice
import platform.UIKit.UIScreen
import platform.Foundation.NSLog

actual class Platform() {
    actual val osName: String
        get() = UIDevice.currentDevice.systemName

    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion

    actual val deviceModel: String
        get() = UIDevice.currentDevice.model

    actual val density: Int
        get() = UIScreen.mainScreen.scale.toInt()

    actual fun logSystemInfo() {
        NSLog("($osName, $osVersion, $deviceModel, $density)")
    }
}