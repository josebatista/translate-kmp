//
//  Colors.swift
//  iosApp
//
//  Created by Jose Pereira on 7/11/25.
//

import Foundation
import SwiftUI
import Shared

extension Color {
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xFF) / 255,
            green: Double((hex >> 08) & 0xFF) / 255,
            blue: Double((hex >> 00) & 0xFF) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = Colors()
    static let lightBlue = Color(hex: colors.LIGHT_BLUE)
    static let lightBlueGrey = Color(hex: colors.LIGHT_BLUE_GREY)
    static let accentViolet = Color(hex: colors.ACCENT_VIOLET)
    static let textBlack = Color(hex: colors.TEXT_BLACK)
    static let darkGrey = Color(hex: colors.DARK_GREY)
    
    static let primary = Color(light: .accentViolet, dark: .accentViolet)
    static let background = Color(light: .lightBlueGrey, dark: .darkGrey)
    static let onPrimary = Color(light: .white, dark: .white)
    static let onBackground = Color(light: .textBlack, dark: .white)
    static let surface = Color(light: .white, dark: .darkGrey)
    static let onSurface = Color(light: .textBlack, dark: .white)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}
