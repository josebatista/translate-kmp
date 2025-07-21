//
//  TranslatorKMPUITests.swift
//  TranslatorKMPUITests
//
//  Created by Jose Pereira on 7/21/25.
//

import XCTest

final class TranslatorKMPUITests: XCTestCase {
    
    private var app: XCUIApplication!

    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.

        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
        app = XCUIApplication()
        app.launchArguments = ["isUiTesting"]
        app.launch()
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    func testRecordAndTranslate() {
        app.buttons["Record audio"].tap()
        
        app.buttons["Voice recorder button"].tap()
        app.buttons["Voice recorder button"].tap()
        
        XCTAssert(app.staticTexts["text result"].waitForExistence(timeout: 2))
        
        app.buttons["Voice recorder button"].tap()
        
        XCTAssert(app.textViews["text result"].waitForExistence(timeout: 2))
        
        app.buttons["TRANSLATE"].tap()
        
        XCTAssert(app.staticTexts["text result"].waitForExistence(timeout: 2))
        XCTAssert(app.staticTexts["test translation"].waitForExistence(timeout: 2))
    }
}
