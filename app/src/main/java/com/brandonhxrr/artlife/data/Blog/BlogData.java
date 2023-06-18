package com.brandonhxrr.artlife.data.Blog;

import android.net.Uri;

public class BlogData {
    Blog[] data = {
            new Blog(Uri.parse("https://mymodernmet.com/wp/wp-content/uploads/2021/09/beyond-monet-exhibition-photos-8.jpg"), "Monet: Luces del impresionismo", "Dylan Rouse", "23/07/2023", ""),
            new Blog(Uri.parse("https://cdn.culturagenial.com/es/imagenes/pinturas-de-vincent-van-gogh-og.jpg"), "Van Gogh: Masters of Art", "Dylan Rouse", "23/07/2023", ""),
            new Blog(Uri.parse("https://www.clarin.com/img/2018/06/19/r1Ycv7KZ7_1256x620__3.jpg#1571928949635"), "DaVinci: Renacentismo", "Dylan Rouse", "23/07/2023", ""),

    };

    public Blog[] getData() {
        return data;
    }
}
