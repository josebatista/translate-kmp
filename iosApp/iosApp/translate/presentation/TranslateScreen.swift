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
    private let parser: VoiceToTextParser
    @ObservedObject var viewModel: iOSTranslateViewModel
    
    init(historyDataSource: HistoryDataSource, translateUseCase: TranslateUseCase, parser: VoiceToTextParser) {
        self.historyDataSource = historyDataSource
        self.translateUseCase = translateUseCase
        self.parser = parser
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
                
                if !viewModel.state.history.isEmpty {
                    Text("History")
                        .font(.title)
                        .bold()
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .listRowSeparator(.hidden)
                        .listRowBackground(Color.background)
                }
                
                ForEach(viewModel.state.history, id: \.self.id) { item in
                    TranslateHistoryItem(
                        item: item,
                        onClick: { viewModel.onEvent(event: .SelectHistoryItem(item: item)) }
                    )
                    .listRowSeparator(.hidden)
                    .listRowBackground(Color.background)
                }
            }
            .listStyle(.plain)
            .buttonStyle(.plain)
            
            VStack {
                Spacer()
                NavigationLink(
                    destination: VoiceToTextScreen(
                        onResult: { spokenText in
                            viewModel.onEvent(event: .SubmitVoiceResult(result: spokenText))
                        },
                        parser: self.parser,
                        languageCode: viewModel.state.fromLanguage.language.langCode
                    )
                ) {
                    ZStack {
                        Circle()
                            .foregroundColor(.primary)
                            .padding()
                        Image(uiImage: UIImage(named: "mic")!)
                            .foregroundColor(.onPrimary)
                            .accessibilityIdentifier("Record audio")
                    }
                    .frame(maxWidth: 100, maxHeight: 100)
                }
            }
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
