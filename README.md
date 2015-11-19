Scala Matsuri
===========================================================

[![Build Status](https://travis-ci.org/scalajp/2016.scalamatsuri.org.svg?branch=gh-pages)](https://travis-ci.org/scalajp/2016.scalamatsuri.org)

このレポジトリは ScalaMatsuri 2016 のウェブサイトです。

## scalamatsuri.org の更新

gh-pagesブランチに変更内容をpushします。

## サイトの管理

ウェブサイトを複数ページに対応させるにあたり、Jekyll を使って作業をするようにします。

### セットアップ

Ruby がインストールされた環境が必要です。

必要な gem をインストールします。

```
gem install bundler
bundle install --path vendor/bundle
```

サーバを起動してブラウザから http://localhost:4000/ にアクセスすると確認できます。
[Jekyllの公式ドキュメント](http://jekyllrb.com/docs/usage/)

```
bundle exec jekyll serve -w
```

### 基本的な設定

_config.yml が基本設定です。

### 公開

[github pages](https://pages.github.com/)を使用しているため、編集内容を git push すれば、自動で反映されます。

### セッション応募ページの追加方法

`_candidates_ja/`, `_candidates_en/` 以下にそれぞれ `(氏名)_(連番).html` を作り、日本語、英語の内容を記載します。
例えば鈴木一郎 (Ichiro Suzuki) さんの最初の応募に対しては `IchiroSuzuki_1.html` となります。

front matter (ファイル先頭の `---` と `---` で囲まれた部分) に次の内容を書きます。

* name (必須): 名前
* title (必須): トークのタイトル
* length (必須): トークの長さ (`15` または `40`)
* audience (必須): 聴衆の対象 (`Beginner`, `Intermediate` または `Advanced`)
* language (必須): 発表言語 (`Japanese` または `English`)
* twitter: Twitter アカウント
* github: Github アカウント
* icon: アイコンのURL
* organization: 所属組織

front matter は YAML 形式です。文字列中に `:` を含む場合は `""` でクォートします。

本文にトークの概要を書きます。以下はテンプレート:

```
---
name: X
title: "X"
length: 40
audience: Intermediate
language: English
twitter: X
github: X
icon: https://X.jpeg
organization: X
---
<p>X
</p>
```

### セッションのタグ変更方法

`_data/cfp_tags.csv` を更新します。
一行目が列名になっています。 `file`, `tag1`, `tag2`, `tag3` は出力時に参照しているので変更しないようにします。

### スポンサーロゴ追加方法

1. ロゴを 200x70px に加工し、 `img/logo/` 以下に保存します。
2. `_data/logos.yml` の該当するプランに次の内容を追記します。

* title: スポンサー名。`<img>` タグの `alt` 属性に指定されます。
* href: リンク先
* img: ロゴ画像URL （どの階層のページでも利用できるように絶対パスで指定する)

例

```
- title: Abc Company
  href: "http://example.com"
  img: "/img/logo/abc.png"
```

logoが200x70ではなかった場合は、200x70に長辺を合わせ余白を白で塗りつぶす。Image Magickを使うと以下のコマンドで一発です。(jpgでも可)

```
convert -resize 200x70 -gravity center -background white -extent 200x70 input.png output.png
```

### 求人情報追加方法

`_jobs/` 以下に `(スポンサー名).md` を作り、求人情報を記載します。

front matter (ファイル先頭の `---` と `---` で囲まれた部分) に次の内容を書きます。

* title (必須): スポンサー名
* logo (必須): 200x70px のロゴ画像URL
* plan (必須): shogun, daimyo, hatamoto, samurai のいずれか
* link: 求人応募先URL
* linkTitle: 求人応募先ボタンの文字列（デフォルト: 「応募する」）

front matter は YAML 形式です。文字列中に `:` を含む場合は `""` でクォートします。

以下はテンプレート:

```
---
title: "X"
logo: "/img/logo/x.png"
plan: shogun
link: "http://example.com"
---
X
```
