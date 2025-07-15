//
//  TranslateTextField.swift
//  iosApp
//
//  Created by Jose Pereira on 7/14/25.
//

import SwiftUI
import Shared
import UniformTypeIdentifiers

struct TranslateTextField: View {
    @Binding var fromText: String
    var toText: String?
    var isTranslating: Bool
    var fromLanguage: UiLanguage
    var toLanguage: UiLanguage
    var onTranslateEvent: (TranslateEvent) -> Void
    
    var body: some View {
        if toText == nil || isTranslating {
            IdleTextField(
                fromText: $fromText,
                isTranslating: isTranslating,
                onTranslateEvent: onTranslateEvent
            )
            .gradientSurface()
            .cornerRadius(15)
            .animation(.easeInOut, value: isTranslating)
            .shadow(radius: 4)
        } else {
            TranslatedTextField(
                fromText: fromText,
                toText: toText ?? "",
                fromLanguage: fromLanguage,
                toLanguage: toLanguage,
                onTranslateEvent: onTranslateEvent
            )
            .padding()
            .gradientSurface()
            .cornerRadius(15)
            .animation(.easeInOut, value: isTranslating)
            .shadow(radius: 4)
            .onTapGesture {
                onTranslateEvent(.EditTranslation())
            }
        }
    }
}

private extension TranslateTextField {
    
    struct IdleTextField: View {
        @Binding var fromText: String
        var isTranslating: Bool
        var onTranslateEvent: (TranslateEvent) -> Void
        
        var body: some View {
            TextEditor(text: $fromText)
                .frame(
                    maxWidth: .infinity,
                    minHeight: 200,
                    alignment: .topLeading
                )
                .padding()
                .foregroundColor(Color.onSurface)
                .overlay(alignment: .bottomTrailing) {
                    ProgressButton(
                        text: "Translate",
                        isLoading: isTranslating,
                        onClick: {
                            onTranslateEvent(.Translate())
                        }
                    )
                    .padding(.trailing)
                    .padding(.bottom)
                }
                .transparentScrolling()
        }
    }
    
    struct TranslatedTextField: View {
        var fromText: String
        var toText: String
        var fromLanguage: UiLanguage
        var toLanguage: UiLanguage
        var onTranslateEvent: (TranslateEvent) -> Void
        
        private let tts = TextToSpeech()
        
        var body: some View {
            VStack(alignment: .leading) {
                LanguageDisplay(language: fromLanguage)
                Text(fromText)
                    .foregroundColor(.onSurface)
                HStack {
                    Spacer()
                    Button(action: {
                        UIPasteboard.general.setValue(
                            fromText,
                            forPasteboardType: UTType.plainText.identifier
                        )
                    }) {
                        Image(uiImage: UIImage(named: "copy")!)
                            .renderingMode(.template)
                            .foregroundColor(.lightBlue)
                    }
                    Button(action: {
                        onTranslateEvent(.CloseTranslation())
                    }) {
                        Image(systemName: "xmark")
                            .foregroundColor(.lightBlue)
                    }
                }
                Divider()
                    .padding()
                LanguageDisplay(language: toLanguage)
                    .padding(.bottom)
                Text(toText)
                    .foregroundColor(.onSurface)
                HStack {
                    Spacer()
                    Button(action: {
                        UIPasteboard.general.setValue(
                            toText,
                            forPasteboardType: UTType.plainText.identifier
                        )
                    }) {
                        Image(uiImage: UIImage(named: "copy")!)
                            .renderingMode(.template)
                            .foregroundColor(.lightBlue)
                    }
                    Button(action: {
                        tts.speak(
                            text: toText,
                            language: toLanguage.language.langCode
                        )
                    }) {
                        Image(systemName: "speaker.wave.2")
                            .foregroundColor(.lightBlue)
                    }
                }
            }
        }
    }
    
}

#Preview {
    TranslateTextField(
        fromText: Binding(
            get: { "test" },
            set: { value in }
        ),
        toText: "Text",
        isTranslating: false,
        fromLanguage: UiLanguage(language: .english, imageName: "english"),
        toLanguage: UiLanguage(language: .german, imageName: "german"),
        onTranslateEvent: { event in }
    )
}
