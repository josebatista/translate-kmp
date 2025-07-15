//
//  ProgressButton.swift
//  iosApp
//
//  Created by Jose Pereira on 7/14/25.
//

import SwiftUI
import Shared

struct ProgressButton: View {
    var text: String
    var isLoading: Bool
    var onClick: () -> Void
    
    var body: some View {
        Button(
            action: {
                if !isLoading {
                    onClick()
                }
            }
        ){
            if isLoading {
                ProgressView()
                    .animation(.easeOut, value: isLoading)
                    .padding(5)
                    .background(Color.primary)
                    .cornerRadius(100)
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
            } else {
                Text(text.uppercased())
                    .animation(.easeInOut, value: isLoading)
                    .padding(.horizontal)
                    .padding(.vertical, 5)
                    .font(.body.weight(.bold))
                    .background(Color.primary)
                    .foregroundStyle(Color.onPrimary)
                    .cornerRadius(100)
            }
        }
    }
}

#Preview {
    ProgressButton(
        text: "TRANSLATE",
        isLoading: true,
        onClick: {}
    )
}
