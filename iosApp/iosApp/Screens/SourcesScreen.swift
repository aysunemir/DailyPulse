//
//  SourceListView.swift
//  iosApp
//
//  Created by Aysun Emir on 27.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import shared
import SwiftUI

extension SourcesScreen {

    @MainActor
    class SourcesViewModelWrapper: ObservableObject {

        init() {
            viewModel = SourcesInjector().sourcesViewModel
            sourceState = viewModel.sourceState.value
        }

        let viewModel: SourcesViewModel

        @Published var sourceState: SourceState

        func startObserving() {
            Task {
                for await sourcesS in viewModel.sourceState {
                    self.sourceState = sourcesS
                }
            }
        }
    }
}

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss

    @ObservedObject private(set) var viewModel: SourcesScreen.SourcesViewModelWrapper

    var body: some View {
        NavigationStack {
            VStack {

                if let error = viewModel.sourceState.error {
                    ErrorMessage(message: error)
                }

                if viewModel.sourceState.loading {
                    Loader()
                }

                if !viewModel.sourceState.sources.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.sourceState.sources, id: \.self) { source in
                                SourceItemView(title: source.title, desc: source.desc, origin: source.languageCounty)
                            }
                        }
                    }
                }
            }.onAppear{
                self.viewModel.startObserving()
            }
            .navigationTitle("Sources")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done")
                            .bold()
                    }
                }
            }
        }
    }
}

struct SourceItemView: View {
    let title: String
    let desc: String
    let origin: String

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.title)
                .fontWeight(.bold)
            Text(desc)
            Text(origin).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}
