package cricketworldcup.icccricketworldcup.Viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import cricketworldcup.icccricketworldcup.R;

public class squad_view_holder extends RecyclerView.ViewHolder {
   public ImageView imageView;
    public squad_view_holder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.squad_row_img);
    }
}
