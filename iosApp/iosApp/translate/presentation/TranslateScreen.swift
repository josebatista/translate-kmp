//
//  TranslateScreen.swift
//  iosApp
//
//  Created by Jose Pereira on 7/14/25.
//

import SwiftUI
import Shared

struct TranslateScreen: View {
    private var historyDataSource: HistoryDataSource
    private var translateUseCase: TranslateUseCase
    @ObservedObject var viewModel: iOSTranslateViewModel
    
    init(historyDataSource: HistoryDataSource, translateUseCase: TranslateUseCase) {
        self.historyDataSource = historyDataSource
        self.translateUseCase = translateUseCase
        self.viewModel = iOSTranslateViewModel(
            historyDataSource: historyDataSource,
            translateUseCase: translateUseCase
        )
    }
    
    var body: some View {
        ZStack {
            List {
                HStack(alignment: .center) {
                    LanguageDropDown(
                        language: viewModel.state.fromLanguage,
                        isOpen: viewModel.state.isChoosingFromLanguage,
                        selectLanguage: { language in viewModel.onEvent(event: .ChooseFromLanguage(language: language)) }
                    )
                    Spacer()
                    SwapLanguageButton(onClick: { viewModel.onEvent(event: .SwapLanguages()) })
                    Spacer()
                    LanguageDropDown(
                        language: viewModel.state.toLanguage,
                        isOpen: viewModel.state.isChoosingToLanguage,
                        selectLanguage: { language in viewModel.onEvent(event: .ChooseToLanguage(language: language)) }
                    )
                }
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)

                TranslateTextField(
                    fromText: Binding(
                        get: {
                            viewModel.state.fromText
                        },
                        set: { value in
                            viewModel.onEvent(event: .ChangeTranslationText(text: value))
                        }
                    ),
                    toText: viewModel.state.toText,
                    isTranslating: viewModel.state.isTranslating,
                    fromLanguage: viewModel.state.fromLanguage,
                    toLanguage: viewModel.state.toLanguage,
                    onTranslateEvent: { event in viewModel.onEvent(event: event) }
                )
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
            }
            .listStyle(.plain)
            .buttonStyle(.plain)
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

//#Preview {
//    TranslateScreen()
//}
