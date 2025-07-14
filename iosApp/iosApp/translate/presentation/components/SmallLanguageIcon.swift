//
//  SmallLanguageIcon.swift
//  iosApp
//
//  Created by Jose Pereira on 7/14/25.
//

import SwiftUI
import Shared

struct SmallLanguageIcon: View {
    var language: UiLanguage
    var body: some View {
        Image(uiImage: UIImage(named: language.imageName.lowercased())!)
            .resizable()
            .frame(width: 30, height: 30)
    }
}

#Preview {
    SmallLanguageIcon(
        language: UiLanguage(language: .german, imageName: "german")
    )
}
