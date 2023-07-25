package com.smartschools.android.ui.util

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View

import com.google.android.material.bottomsheet.BottomSheetDialog


/**
 * A group of *members*.
 *
 * This class has is used to present a bottom sheet dialog with two optional buttons
 * negative and positive buttons, both buttons can be labeled from the builder of the class
 * this dialog is using Builder design pattern to build the dialog with its components
 *
 * here is an example how to use the class it self without any further customizations
 *  val dialog = UserMessageDialog.Builder()
 *               .setDialogTitle("Title")
 *               .setDialogMessage("Dialog Message")
 *               .setNegativeButtonText("Cancel")
 *               .setNegativeButtonClickListener {}
 *               .setPositiveButtonText("Accept")
 *               .setPositiveButtonClickListener {}
 *               .build(requireContext())
 *               .apply { show() }
 *
 * @property dialogTitle the Title of the dialog.
 * @property dialogMessage the message that is displayed in the middle of the dialog.
 * @property positiveButtonText the text on the positive button (right button).
 * @property positiveClickListener lambda expression to present the click handler of the button in the view.
 * @property negativeButtonText the text on the negative button (left button).
 * @property negativeClickListener lambda expression to present the click handler of the button in the view.
 * @constructor the constructor of the class is private and cannot be used outside the class.
 */

//class UserMessageDialog private constructor(
//    context: Context,
//    private var dialogTitle: String? = null,
//    private var dialogMessage: String? = null,
//    private var negativeButtonText: String? = null,
//    private var negativeClickListener: (() -> Unit)? = null,
//    private var positiveButtonText: String? = null,
//    private var positiveClickListener: (() -> Unit)? = null
//) :
//    BottomSheetDialog(context, R.style.BottomSheetDialogTheme) {
//
//    val binding: DialogUserMessageBinding =
//        DialogUserMessageBinding.inflate(layoutInflater, null, false)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        this.setCancelable(true)
//        Log.d("MessageDialog", "onCreate: messageDialogCreated")
//
//        binding.titleTextView.text = dialogTitle
//        binding.messageTextView.text = dialogMessage
//
//        if(negativeButtonText != null){
//            binding.cancelButton.text = negativeButtonText
//            binding.cancelButton.visibility = View.VISIBLE
//        }
//
//        if(positiveButtonText != null){
//            binding.acceptButton.text = positiveButtonText
//            binding.acceptButton.visibility = View.GONE
//        }
//
//        binding.acceptButton.setOnClickListener {
//            this.dismiss()
//            positiveClickListener?.let { it1 -> it1() }
//        }
//
//        binding.cancelButton.setOnClickListener {
//            this.dismiss()
//            negativeClickListener?.let { it1 -> it1() }
//        }
//
//
//    }
//
//
//    data class Builder(
//        private var dialogTitle: String? = null,
//        private var dialogMessage: String? = null,
//        private var negativeButton: String? = null,
//        private var negativeClick: (() -> Unit)? = null,
//        private var positiveButton: String? = null,
//        private var positiveClick: (() -> Unit)? = null
//    ) {
//        fun setDialogTitle(title: String) = apply { this.dialogTitle = title }
//
//        fun setDialogMessage(message: String) = apply { this.dialogMessage = message }
//
//        fun setNegativeButtonText(negativeButton: String) =
//            apply { this.negativeButton = negativeButton }
//
//        fun setNegativeButtonClickListener(negativeClick: () -> Unit) =
//            apply { this.negativeClick = negativeClick }
//
//        fun setPositiveButtonText(positiveButton: String) =
//            apply { this.positiveButton = positiveButton }
//
//        fun setPositiveButtonClickListener(positiveClick: () -> Unit) =
//            apply { this.positiveClick = positiveClick }
//
//
//        fun build(context: Context) = UserMessageDialog(
//            context,
//            dialogTitle,
//            dialogMessage,
//            negativeButton,
//            negativeClick,
//            positiveButton,
//            positiveClick
//        ).apply {
//
//            show() }
//
//
//    }
//
//}