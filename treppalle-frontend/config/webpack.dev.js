var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var commonConfig = require('./webpack.common.js');
var helpers = require('./helpers');
 
module.exports = webpackMerge(commonConfig, {
  devtool: 'cheap-module-eval-source-map',
 
  output: {
    path: helpers.root('dist'),
    publicPath: '/',
    filename: '[name].[hash].bundle.js',
    chunkFilename: '[id].[hash].bundle.js'
  },
 
  plugins: [
    new ExtractTextPlugin('[name].[hash].css')
  ],
 
  devServer: {
    historyApiFallback: true,
    stats: 'minimal'
  }
});
