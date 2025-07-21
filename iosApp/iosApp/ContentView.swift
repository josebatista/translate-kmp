import SwiftUI
import Shared

struct ContentView: View {
    let appModule: AppModule
    var body: some View {
        ZStack {
            Color.background
                .ignoresSafeArea()
            TranslateScreen(
                historyDataSource: appModule.historyDataSource,
                translateUseCase: appModule.translateUseCase,
                parser: appModule.voiceParser
            )
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(appModule: TestAppModule())
    }
}
