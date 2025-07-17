//
//  VoiceToTextScreen.swift
//  iosApp
//
//  Created by Jose Pereira on 7/17/25.
//

import SwiftUI
import Shared

struct VoiceToTextScreen: View {
    private let onResult: (String) -> Void
    private let parser: any VoiceToTextParser
    private let languageCode: String
    @ObservedObject var viewModel: iOSVoiceToTextViewModel
    @Environment(\.presentationMode) var presentationMode
    
    init(onResult: @escaping (String) -> Void, parser: any VoiceToTextParser, languageCode: String) {
        self.onResult = onResult
        self.parser = parser
        self.languageCode = languageCode
        self.viewModel = iOSVoiceToTextViewModel(parser: parser, languageCode: languageCode)
    }
    
    var body: some View {
        VStack {
            Spacer()
            mainView
            Spacer()
            
            HStack {
                Spacer()
                VoiceRecorderButton(
                    displayState: viewModel.state.displayState ?? .waitingToTalk,
                    onClick: {
                        if viewModel.state.displayState != .displayingResults {
                            viewModel.onEvent(event: .ToggleRecording(languageCode: self.languageCode))
                        } else {
                            onResult(viewModel.state.spokenText)
                            self.presentationMode.wrappedValue.dismiss()
                        }
                    }
                )
                if viewModel.state.displayState == .displayingResults {
                    Button(action: {
                        viewModel.onEvent(event: .ToggleRecording(languageCode: self.languageCode))
                    }) {
                        Image(systemName: "arrow.clockwise")
                            .foregroundColor(.lightBlue)
                    }
                }
                Spacer()
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .background(Color.background)
    }
    
    var mainView: some View {
        if let displayState = viewModel.state.displayState {
            switch displayState {
            case .waitingToTalk:
                return AnyView(
                    Text("Click record and start talking.")
                        .font(.title2)
                )
            case .displayingResults:
                return AnyView(
                    Text(viewModel.state.spokenText)
                        .font(.title2)
                )
            case .error:
                return AnyView(
                    Text(viewModel.state.recordError ?? "Unknown error occurred.")
                        .font(.title2)
                        .foregroundColor(.red)
                )
            case .speaking:
                return AnyView(
                    VoiceRecorderDisplay(
                        powerRatios: viewModel.state.powerRatios.map { Double(truncating: $0) }
                    )
                    .frame(maxHeight: 100)
                    .padding()
                )
            default:
                return AnyView(EmptyView())
            }
        } else {
            return AnyView(EmptyView())
        }
    }
}

//#Preview {
//    VoiceToTextScreen()
//}
