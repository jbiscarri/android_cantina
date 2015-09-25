package com.biscarri.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.biscarri.cantina.R;
import com.biscarri.model.Plate;

import java.lang.ref.WeakReference;

/**
 * Created by joanbiscarri on 24/09/15.
 */
public class AddPlateDialogFragment extends DialogFragment {

    protected TextInputLayout mTextInputLayout;
    protected EditText mComment;
    protected WeakReference<AddPlateDialogListener> mAddPlateDialogListener;
    protected Plate mPlate;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_plate, null);

        dialog.setView(dialogView);

        dialog.setPositiveButton(android.R.string.ok, null);
        dialog.setNegativeButton(android.R.string.cancel, null);

        mTextInputLayout = (TextInputLayout) dialogView.findViewById(R.id.text_input_layout);
        mComment = (EditText) dialogView.findViewById(R.id.textDialog);
        return dialog.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAddPlateDialogListener != null && mAddPlateDialogListener.get() != null) {
                        mAddPlateDialogListener.get().onPlateAddedListener(AddPlateDialogFragment.this, mPlate, mComment.getText().toString());
                    }
                    dialog.dismiss();
                }
            });

            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);
            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }

    public void setPlate(Plate plate) {
        mPlate = plate;
        //TODO set plate data
    }

    public void setAddPlateDialogListener(AddPlateDialogListener addPlateDialogListener) {
        mAddPlateDialogListener = new WeakReference<>(addPlateDialogListener);
    }

    public interface AddPlateDialogListener {
        void onPlateAddedListener(AddPlateDialogFragment dialog, Plate plate, String comment);
    }


}
