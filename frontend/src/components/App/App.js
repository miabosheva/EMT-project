import "./App.css";
import React from "react";
import {BrowserRouter as Router, Routes, Route, Switch} from "react-router-dom";
import LibraryService from "../../repository/libraryRepository";
import Books from "../Books/BooksList/books";
import AddBook from "../Books/BooksAdd/booksAdd";
import Header from "../Header/header";
import EditBook from "../Books/BooksEdit/booksEdit";
import Category from "../Categories/listCategories";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            categories: [],
            books: [],
            selectedBook: {},
        };
    }

    render() {
        return (
            <Router>
                <main>
                    <Header/>
                    <Switch>
                        <Route
                            path={"/edit/:id"}
                            exact
                            render={() =>
                            <EditBook
                                categories={this.state.categories}
                                authors={this.state.authors}
                                book={this.state.selectedBook}
                                onEditBook={this.editBook}
                            />
                        }
                        />
                        <Route
                            path={"/books/addBook"}
                            exact render={() =>
                            <AddBook
                                addBook={this.addBook}
                                categories={this.state.categories}
                                authors={this.state.authors}
                            />
                        }
                        />
                        <Route
                            path={"/books"}
                            exact render={() =>
                            <Books
                                books={this.state.books}
                                markAsTaken={this.markAsTaken}
                                onDelete={this.deleteBook}
                                onEdit={this.getBook}
                            />
                        }
                        />
                        <Route
                            path={"/categories"}
                            exact render={() => <Category categories={this.state.categories}/>}
                        />
                        <Route
                            path={"/"}
                            exact render={() =>
                            <Books
                                books={this.state.books}
                                onMarkAsTaken={(id) => this.markAsTaken(id)}
                                onDelete={this.deleteBook}
                                onEdit={this.getBook}
                            />}
                        />
                    </Switch>

                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadAuthors();
        this.loadCategories();
        this.loadBooks();
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors().then((data) => {
            this.setState({
                authors: data.data,
            });
        });
    };

    loadCategories = () => {
        LibraryService.fetchCategories().then((data) => {
            this.setState({
                categories: data.data,
            });
        });
    };

    loadBooks = () => {
        LibraryService.fetchBooks().then((data) => {
            this.setState({
                books: data.data,
            });
        });
    };

    addBook = (name, category, authorId, availableCopies) => {
        LibraryService.addBook(name, category, authorId, availableCopies).then(
            () => {
                this.loadBooks();
            }
        );
    };

    editBook = (id, name, category, authorId, availableCopies) => {
        LibraryService.editBook(id, name, category, authorId, availableCopies).then(
            () => {
                this.loadBooks();
            }
        );
    };

    getBook = (id) => {
        LibraryService.getBook(id).then((data) => {
            this.setState({
                selectedBook: data.data,
            });

        });
    };

    deleteBook = (id) => {
        LibraryService.deleteBook(id).then(() => {
            this.loadBooks();
        });
    };

    markAsTaken = (id) => {
        LibraryService.markAsTaken(id).then(() => {
            this.loadBooks();
        });
    };
}

export default App;