package com.padcmyanmar.thiha.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object LibraryModelImpl: BasedModel(), LibraryModel {
    override fun getBookListsFromDatabase(onFail: (String) -> Unit): LiveData<List<BookListVO>>? {
        mTheLibraryApi.getBookList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results.lists?.forEach { bookList ->
                    bookList.books?.forEach { book ->
                        book.bookListName = bookList.listName.toString()
                    }
                }
                response.results.lists?.let {
                    mLibraryDatabase?.bookListDao()?.insertAllBookList(bookList = it)

                }
            }, {
                it.localizedMessage?.let { it1 -> onFail(it1.toString()) }
            }
            )

        return mLibraryDatabase?.bookListDao()?.getAllBookList()

    }


    override fun getBookListByListName(
        listName: String,
        onSuccess: (List<BookVO>) -> Unit,
        onFail: (String) -> Unit
    ) {
        mTheLibraryApi.getBookListByListName(list = listName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    val bookList : ArrayList<BookVO> = arrayListOf()
                    response.results.forEach {
                        it.bookDetails?.firstOrNull()?.let { bookVO ->
                            bookList.add(bookVO)
                        }
                    }
                    onSuccess(bookList)
                },
                {
                    it.localizedMessage?.let { it1 -> onFail(it1.toString()) }
                }
            )
    }

    override fun searchBook(query: String): Observable<List<BookVO>> {
        return mTheLibraryApi.searchBooks(query = query)
            .map{ it.items?.map { googleBook -> googleBook.transformVO() } ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }

    override fun insertRecentBookToDatabase(bookVO: BookVO) {
           bookVO.dateMillis = System.currentTimeMillis()
        mLibraryDatabase?.recentBookDao()?.insertSingleBook(bookVO)
    }


    override fun getRecentBookListFromDatabase(): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.recentBookDao()?.getRecentBookList()
    }

    override fun getRecentBookListFromDatabaseOneTime(): List<BookVO>? {
        return mLibraryDatabase?.recentBookDao()?.getRecentBookListOneTime()
    }

    override fun getAllRecentBookByCategory(category: String): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.recentBookDao()?.getAllRecentBookByCategory(category)

    }

    override fun getAllRecentBookByCategoryOneTime(category: String): List<BookVO>? {
        return mLibraryDatabase?.recentBookDao()?.getAllRecentBookByCategoryOneTime(category)
    }

    override fun deleteRecentBookByName(bookName: String) {
        mLibraryDatabase?.recentBookDao()?.deleteRecentBookByName(bookName)
    }

    override fun insertShelfToDatabase(shelfVO: ShelfVO) {
        mLibraryDatabase?.shelfDao()?.insertShelf(shelfVO)
    }

    override fun getAllShelves(): LiveData<List<ShelfVO>>? {
        return mLibraryDatabase?.shelfDao()?.getAllShelves()
    }

    override fun insertShelfListToDatabase(shelfList: List<ShelfVO>) {
        mLibraryDatabase?.shelfDao()?.insertShelfList(shelfList)
    }

    override fun getShelfById(id: Long): LiveData<ShelfVO?>? {
        return mLibraryDatabase?.shelfDao()?.getShelfById(id)

    }

    override fun deleteShelf(id: Long) {
        mLibraryDatabase?.shelfDao()?.deleteShelfById(id)
    }





}














//    override fun getRecentBookForCarouselFromDatabase(bookName: String): LiveData<BookVO>? {
//        return mLibraryDatabase?.recentBookDao()?.getRecentBookForCarousel(bookName)
//    }
//
//    override fun getRecentBookForCarouselFromDatabaseOneTime(bookName: String): BookVO? {
//        return mLibraryDatabase?.recentBookDao()?.getRecentBookForCarouselOneTime(bookName)
//    }