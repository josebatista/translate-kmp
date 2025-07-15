//
//  LanguageDisplay.swift
//  iosApp
//
//  Created by Jose Pereira on 7/14/25.
//

import SwiftUI
import Shared

struct LanguageDisplay: View {
    var language: UiLanguage
    var body: some View {
        HStack{
            SmallLanguageIcon(language: language)
                .padding(.trailing, 5)
            Text(language.language.langName)
                .foregroundColor(.lightBlue)
        }
    }
}

#Preview {
    LanguageDisplay(
        language: UiLanguage(language: .english, imageName: "english")
    )
}
