package cricketworldcup.icccricketworldcup.Viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cricketworldcup.icccricketworldcup.R;

public class fixer_view_holder extends RecyclerView.ViewHolder {

public   TextView teamone,teamtwo,venue,time;
public ImageView imageView;
    public fixer_view_holder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img);
        teamone = itemView.findViewById(R.id.fixture_teamone);
        teamtwo = itemView.findViewById(R.id.fixture_teamtwo);
        venue = itemView.findViewById(R.id.fixture_venue);
        time = itemView.findViewById(R.id.fixture_time);
    }
}
