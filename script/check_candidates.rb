#!/usr/bin/env ruby
# Check front matter of candidate files

require 'yaml'

def files(lang)
  Dir.glob("_candidates_#{lang}/*.html")
end

def load_yamls(lang)
  Hash[files(lang).map { |f| [f, YAML.load_file(f)] }]
end

def check_github(fm)
  g = fm['github']
  "Invalid github: #{g}" if !g.nil? && g !~ /^[0-9a-z\-]+$/i
end

def check_twitter(fm)
  t = fm['twitter']
  "Invalid twitter: #{t}" if !t.nil? && t !~ /^[0-9a-z_]+$/i
end

def check_language(fm)
  l = fm['language']
  "Invalid language: #{l}" unless %w(Japanese English English/Japanese).include?(l)
end

def check_length(fm)
  l = fm['length']
  "Invalid length: #{l}" unless [15, 40, 50].include?(l)
end

def check_audience(fm)
  a = fm['audience']
  "Invalid audience: #{a}" unless %w(Beginner Intermediate Advanced).include?(a)
end

def check_icon(fm)
  i = fm['icon']
  "Invalid icon: #{i}" if !i.nil? && i !~ /^(https?:\/\/|\/)/
end

if __FILE__ == $0
  ja = load_yamls('ja')
  en = load_yamls('en')
  all = ja.merge(en)

  checks = [
      :check_github, :check_twitter, :check_language, :check_length, :check_audience,
      :check_icon
  ]

  errors = []
  all.each { |f, fm|
    checks.each { |c|
      error = method(c).call(fm)
      errors << [f, error] unless error.nil?
    }
  }

  if errors.size > 0
    errors.each { |err| puts "#{err[1]} at #{err[0]}" }
    exit(1)
  end

end
