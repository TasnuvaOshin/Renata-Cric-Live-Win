package cricketworldcup.icccricketworldcup.UserEnd.Matches.Winner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cricketworldcup.icccricketworldcup.R;

public class winnerViewHolder extends RecyclerView.ViewHolder {
   public TextView textView;

    public winnerViewHolder(@NonNull View itemView) {
        super(itemView);
        textView  = itemView.findViewById(R.id.winner);
    }
}
