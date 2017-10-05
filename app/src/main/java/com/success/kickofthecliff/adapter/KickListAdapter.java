package com.success.kickofthecliff.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.success.kickofthecliff.MainActivity;
import com.success.kickofthecliff.R;
import com.success.kickofthecliff.dto.KickDTO;
import com.success.kickofthecliff.dto.KickWinterDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KickListAdapter extends RecyclerView.Adapter<KickListAdapter.KickViewHolder>{

    private static List<KickDTO> data;


    public KickListAdapter(List<KickDTO> data) {//конструктор для получения данных
        this.data = data;
    }

    @Override
    public KickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//создание карточек
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kick_item, parent, false);

        return new KickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KickViewHolder holder, int position) {//передача данных в карточки
        holder.title.setText(data.get(position).getTitle());
        holder.info.setText(data.get(position).getKickInfo());

        String url = String.valueOf(data.get(position).getPhoto());//получаем ссылку из KickDTO

        //загрузка изображения в imageView с помощью библиотеки Picasso
        Picasso.with(holder.itemView.getContext())
                .load(url)
                .placeholder(R.drawable.sleep)
                .error(R.drawable.close_outline)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {//количество айтемов (карточек)
        return data.size();
    }

    public static class KickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{//поиск карточек по Id

        CardView cardView;
        TextView title;
        TextView info;
        ImageView imageView;


        public KickViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            info = (TextView) itemView.findViewById(R.id.textInfo);
            imageView = (ImageView) itemView.findViewById(R.id.photo);

            cardView.setOnClickListener(this);
        }

        //метод для взаимодействия с картами
        @Override
        public void onClick(View v) {
            MainActivity.start(v.getContext(), getAdapterPosition(), data);//переход
            Log.d("RecyclerView", "Вы щёлкнули на позиции " + (getAdapterPosition() + 1));
        }

    }
    public void setData(List<KickDTO> data) {//сеттер данных
        this.data = data;
    }
}
