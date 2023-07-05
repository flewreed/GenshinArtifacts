package com.example.genshinartifacts.setsInfoPage;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.objectModels.Artifact;
import com.example.genshinartifacts.objectModels.ArtifactsSet;
import com.example.genshinartifacts.objectModels.ConstantData;

import java.util.ArrayList;

public class ArtifactInfoActivity extends AppCompatActivity {

	private ArtifactInfoActivity artifactInfoActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artifact_info);
		Intent intent = getIntent();
		int id = intent.getExtras().getInt("id");

		setSetData(id);
	}

	private void setSetData(int id) {
		ArtifactsSet set = ConstantData.findSetById(id);

		ImageView set_img = findViewById(R.id.setImage);
		TextView set_name = findViewById(R.id.setName);
		TextView bonus2 = findViewById(R.id.bonus2_text);
		TextView bonus4 = findViewById(R.id.bonus4_text);

		ImageView art_img1 = findViewById(R.id.artifact_set_image);
		ImageView art_img_sh1 = findViewById(R.id.artifact_set_image_shadow);
		TextView art_name_1 = findViewById(R.id.art_name1);

		ImageView art_img2 = findViewById(R.id.artifact_set_image2);
		ImageView art_img_sh2 = findViewById(R.id.artifact_set_image_shadow2);
		TextView art_name_2 = findViewById(R.id.art_name2);

		ImageView art_img3 = findViewById(R.id.artifact_set_image3);
		ImageView art_img_sh3 = findViewById(R.id.artifact_set_image_shadow3);
		TextView art_name_3 = findViewById(R.id.art_name3);

		ImageView art_img4 = findViewById(R.id.artifact_set_image4);
		ImageView art_img_sh4 = findViewById(R.id.artifact_set_image_shadow4);
		TextView art_name_4 = findViewById(R.id.art_name4);

		ImageView art_img5 = findViewById(R.id.artifact_set_image5);
		ImageView art_img_sh5 = findViewById(R.id.artifact_set_image_shadow5);
		TextView art_name_5 = findViewById(R.id.art_name5);

		set_img.setImageBitmap(BitmapFactory.decodeByteArray(set.getImg(), 0, set.getImg().length));
		set_name.setText(set.getName());

		SpannableStringBuilder sb2 = new SpannableStringBuilder(set.getBonus_2());
		SpannableStringBuilder sb4 = new SpannableStringBuilder(set.getBonus_4());
		StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // span to make text bold
        sb2.setSpan(bss, 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 11 characters Bold
		sb4.setSpan(bss, 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 11 characters Bold
		bonus2.setText(sb2);
		bonus4.setText(sb4);

		ArrayList<Artifact> art_set = ConstantData.findArtifactsBySetId(id);
		art_img1.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 5).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 5).getImg().length));
		art_img_sh1.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 5).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 5).getImg().length));
		art_name_1.setText(ConstantData.findArtifactFromListByType(art_set, 5).getName());

		art_img2.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 4).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 4).getImg().length));
		art_img_sh2.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 4).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 4).getImg().length));
		art_name_2.setText(ConstantData.findArtifactFromListByType(art_set, 4).getName());

		art_img3.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 3).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 3).getImg().length));
		art_img_sh3.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 3).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 3).getImg().length));
		art_name_3.setText(ConstantData.findArtifactFromListByType(art_set, 3).getName());

		art_img4.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 1).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 1).getImg().length));
		art_img_sh4.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 1).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 1).getImg().length));
		art_name_4.setText(ConstantData.findArtifactFromListByType(art_set, 1).getName());

		art_img5.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 2).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 2).getImg().length));
		art_img_sh5.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactFromListByType(art_set, 2).getImg(), 0, ConstantData.findArtifactFromListByType(art_set, 2).getImg().length));
		art_name_5.setText(ConstantData.findArtifactFromListByType(art_set, 2).getName());
	}
}




















