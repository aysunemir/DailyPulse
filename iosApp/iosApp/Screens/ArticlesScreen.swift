//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Aysun Emir on 25.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import shared
import SwiftUI

extension ArticlesScreen {

    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel


        init() {
            articlesViewModel = ArticlesInjector().articlesViewModel
            articleState = articlesViewModel.articleState.value
        }

        @Published var articleState: ArticleState

        func startObserving() {
            Task {
                for await articleS in articlesViewModel.articleState {
                    self.articleState = articleS
                }
            }
        }
    }
}

struct ArticlesScreen: View {

    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper

    var body: some View {
        VStack {
            AppBar()

            if viewModel.articleState.loading {
                Loader()
            }

            if let error = viewModel.articleState.error {
                ErrorMessage(message: error)
            }

            if(!viewModel.articleState.articles.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articleState.articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }

        }.onAppear{
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct ArticleItemView: View {
    var article: Article

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String

    var body: some View {
        Text(message)
            .font(.title)
    }
}
