//
//  iOSVoiceToTextViewModel.swift
//  iosApp
//
//  Created by Jose Pereira on 7/17/25.
//

import Foundation
import Combine
import Shared

@MainActor class iOSVoiceToTextViewModel: ObservableObject {
    private var handle: DisposableHandle?
    private var parser: any VoiceToTextParser
    private let languageCode: String
    private let viewModel: VoiceToTextViewModel
    @Published var state = VoiceToTextState(
        powerRatios: [],
        spokenText: "",
        canRecord: false,
        recordError: nil,
        displayState: nil
    )
    
    init(parser: VoiceToTextParser, languageCode: String) {
        self.parser = parser
        self.languageCode = languageCode
        self.viewModel = VoiceToTextViewModel(parser: parser, coroutineScope: nil)
    }
    
    func onEvent(event: VoiceToTextEvent) {
        viewModel.onEvent(event: event)
    }
    
    func startObserving() {
        self.handle = viewModel.state.subscribe { [weak self] state in
            if let state {
                self?.state = state
            }
        }
    }
    
    func dispose() {
        self.handle?.dispose()
        onEvent(event: .Reset())
    }
}
