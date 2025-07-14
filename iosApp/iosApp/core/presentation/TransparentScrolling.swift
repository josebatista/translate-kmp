//
//  TransparentScrolling.swift
//  iosApp
//
//  Created by Jose Pereira on 7/14/25.
//

import SwiftUI

extension View {
    func transparentScrolling() -> some View {
        if #available(iOS 16.0, *) {
            return scrollContentBackground(.hidden)
        } else {
            return onAppear {
                UITextView.appearance().backgroundColor = .clear
            }
        }
    }
}
