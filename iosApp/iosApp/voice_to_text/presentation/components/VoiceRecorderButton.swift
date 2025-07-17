//
//  VoiceRecorderButton.swift
//  iosApp
//
//  Created by Jose Pereira on 7/17/25.
//

import SwiftUI
import Shared

struct VoiceRecorderButton: View {
    var displayState: DisplayState
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick) {
            ZStack {
                Circle()
                    .foregroundColor(.primary)
                    .padding()
                icon
                    .foregroundColor(.onPrimary)
            }
        }
        .frame(maxWidth: 100.0, maxHeight: 100.0)
    }
    
    var icon: some View {
        switch displayState {
        case .speaking:
            return Image(systemName: "stop.fill")
        case .displayingResults:
            return Image(systemName: "checkmark")
        default:
            return Image(uiImage: UIImage(named: "mic")!)
        }
    }
}

#Preview {
    VoiceRecorderButton(
        displayState: .waitingToTalk,
        onClick: {}
    )
}
