package bgi.com.bgi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by VPS on 15-12-2017.
 */

public class AddPhotoBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int SELECT_PICTURE = 0;
    public String selectedImagePath;

    private View view;
    /**
     * Add Photo
     */
    private TextView mTvBottomSheetHeading;
    /**
     * Use Camera
     */
    private TextView mTvBtnAddPhotoCamera;
    /**
     * Upload from Gallery
     */
    private TextView mTvBtnAddPhotoGallery;
    /**
     * Remove Photo
     */
    private TextView mTvBtnRemovePhoto;

    public static AddPhotoBottomDialogFragment newInstance() {
        return new AddPhotoBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom, container, false);
        // get the views and attach the listener
        initView(view);
        return view;

    }

    private void initView(View view) {
        mTvBottomSheetHeading = (TextView) view.findViewById(R.id.tv_bottom_sheet_heading);
        mTvBtnAddPhotoCamera = (TextView) view.findViewById(R.id.tv_btn_add_photo_camera);
        mTvBtnAddPhotoCamera.setOnClickListener(this);
        mTvBtnAddPhotoGallery = (TextView) view.findViewById(R.id.tv_btn_add_photo_gallery);
        mTvBtnAddPhotoGallery.setOnClickListener(this);
        mTvBtnRemovePhoto = (TextView) view.findViewById(R.id.tv_btn_remove_photo);
        mTvBtnRemovePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_btn_add_photo_camera:
                dispatchTakePictureIntent();
                break;
            case R.id.tv_btn_add_photo_gallery:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
                break;
            case R.id.tv_btn_remove_photo:
                break;
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                //selectedImagePath = getPath(selectedImageUri);
            }
        }
    }
}
