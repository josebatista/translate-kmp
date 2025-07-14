//
//  LanguageDropDownItem.swift
//  iosApp
//
//  Created by Jose Pereira on 7/11/25.
//

import SwiftUI
import Shared

struct LanguageDropDownItem: View {
    var language: UiLanguage
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick) {
            HStack {
                if let image = UIImage(named: language.imageName.lowercased()) {
                    Image(uiImage: image)
                        .resizable()
                        .frame(width: 40, height: 40)
                        .padding(.trailing, 5)
                    Text(language.language.langName)
                        .foregroundColor(.textBlack)
                }
            }
        }
    }
}

#Preview {
    LanguageDropDownItem(
        language: UiLanguage(language: .portuguese, imageName: "portuguese"),
        onClick: {}
    )
}
