//
//  SwapLanguageButton.swift
//  iosApp
//
//  Created by Jose Pereira on 7/14/25.
//

import SwiftUI

struct SwapLanguageButton: View {
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick) {
            Image(uiImage: UIImage(named: "swap_languages")!)
                .padding()
                .background(Color.primary)
                .clipShape(Circle())
        }
    }
}

#Preview {
    SwapLanguageButton(
        onClick: {}
    )
}
