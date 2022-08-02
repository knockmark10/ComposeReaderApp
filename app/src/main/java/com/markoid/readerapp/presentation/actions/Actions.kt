package com.markoid.readerapp.presentation.actions

import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.data.entities.UserFormEntry

typealias OnAuthDone = (UserFormEntry) -> Unit

typealias OnHomeFabTapAction = (String) -> Unit

typealias OnBookClicked = (Book) -> Unit

typealias OnSimpleClick = () -> Unit