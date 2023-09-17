package com.aritra.notify.components.topbar


import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.aritra.notify.R
import com.aritra.notify.components.actions.ShareOption
import com.aritra.notify.data.models.Note
import com.aritra.notify.ui.screens.notes.addNoteScreen.AddNoteViewModel
import com.aritra.notify.utils.shareAsImage
import com.aritra.notify.utils.shareAsPdf
import com.aritra.notify.utils.shareNoteAsText
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteTopBar(
    viewModel: AddNoteViewModel,
    onBackPress: () -> Unit,
    onSave: () -> Unit,
    title: String,
    description: String,
    dateTime: Date,
    imagePath: List<Bitmap>?,
) {
    var showSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val view = LocalView.current
    val bitmapSize = view.width to view.height



    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {
            Text(
                text = "",
                fontFamily = FontFamily(Font(R.font.poppins_semibold))
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPress() }) {
                Icon(
                    painterResource(R.drawable.back),
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        actions = {
            if (title.isNotEmpty() && description.isNotEmpty()) {
                IconButton(onClick = { showSheet = true }) {
                    Icon(
                        painterResource(R.drawable.ic_share),
                        contentDescription = stringResource(R.string.share)
                    )
                }
                if (showSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showSheet = false },
                        sheetState = bottomSheetState,
                        dragHandle = { BottomSheetDefaults.DragHandle() }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding()
                                .padding(16.dp)
                        ) {
                            Spacer(modifier = Modifier.height(5.dp))
                            ShareOption(
                                text = stringResource(R.string.share_note_as_text),
                                onClick = {
                                    shareNoteAsText(context, title, description)
                                    showSheet = false
                                }
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            ShareOption(
                                text = stringResource(R.string.share_note_as_picture),
                                onClick = {
                                    shareAsImage(view, bitmapSize)
                                    showSheet = false
                                }
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            ShareOption(
                                text = stringResource(R.string.share_as_pdf),
                                onClick = {
                                    shareAsPdf(view, "Notify")
                                    showSheet = false
                                }
                            )
                        }
                    }
                }

                IconButton(onClick = {
                    val noteDB = Note(id = 0, title = title, note = description, dateTime = dateTime, imagePath = imagePath)
                    Log.d("","Image path is :$imagePath")
                    viewModel.insertNote(noteDB)
                    onSave()
                    Toast.makeText(context, "Successfully Saved!", Toast.LENGTH_SHORT).show()

                }) {
                    Icon(
                        painterResource(R.drawable.save),
                        contentDescription = stringResource(R.string.save)
                    )
                }
            }
        }
    )
}

